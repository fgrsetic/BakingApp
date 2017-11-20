package com.franjo.android.bakingapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.franjo.android.bakingapp.R;
import com.franjo.android.bakingapp.model.Recipes;
import com.franjo.android.bakingapp.model.Steps;
import com.franjo.android.bakingapp.utilities.Constants;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StepDetailFragment extends Fragment {

    private static final String TAG = StepDetailFragment.class.getSimpleName();

    private List<Recipes> mListRecipes;
    private List<Steps> mListSteps;

    private SimpleExoPlayer mPlayer;
    private DefaultBandwidthMeter bandwithMeter;
    private static MediaSessionCompat mMediaSession;

    private int mListIndex;
    private String mRecipeName;

    @BindView(R.id.tvDescription)
    TextView tvDescription;

    @BindView(R.id.mPlayerView)
    SimpleExoPlayerView mPlayerView;

    @BindView(R.id.thumbImage)
    ImageView mThumbnailImage;

    @BindView(R.id.btnPrevious)
    Button btnPrevious;

    @BindView(R.id.btnNext)
    Button btnNext;

    // Define a new interface OnStepDetailListener that triggers a callback in the host activity
    OnStepDetailListener mButtonCallback;


    // Mandatory empty constructor
    public StepDetailFragment() {

    }




    public interface OnStepDetailListener {
        void onStepSelected(List<Steps> stepsList, int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mButtonCallback = (OnStepDetailListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepDetailListener");
        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListRecipes = new ArrayList<>();
        mListSteps = new ArrayList<>();
        bandwithMeter = new DefaultBandwidthMeter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        mButtonCallback = (RecipeDetailActivity)getActivity();

        mRecipeName = ((RecipeDetailActivity)getActivity()).mRecipeName;

        if (savedInstanceState != null) {
            mListRecipes = savedInstanceState.getParcelableArrayList(Constants.CLICKED_RECIPE);
            mListSteps = savedInstanceState.getParcelableArrayList(Constants.CLICKED_STEP);
            mListIndex = savedInstanceState.getInt("Index");

        } else {
            mListSteps = bundle.getParcelableArrayList(Constants.CLICKED_STEP);
            if(mListSteps != null) {
                mListSteps = bundle.getParcelableArrayList(Constants.CLICKED_STEP);
                mListIndex = bundle.getInt("Index");

            } else {
                mListRecipes = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);
                if (mListRecipes != null) {
                    mListSteps = mListRecipes.get(0).getSteps();
                    mListIndex = 0;
                }

            }
        }



        // Initialize the Media Session.
        initializeMediaSession();


        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        ButterKnife.bind(this, fragmentView);

        setDetailedStepFragment(mListSteps, mListIndex);



            btnPrevious.setOnClickListener(new View.OnClickListener() {

                int id = mListSteps.get(mListIndex).getId();
                int indexPosition = id - 1;

                @Override
                public void onClick(View view) {

                    if (mListSteps != null) {
                        if (id > 0) {
                            if (mPlayer != null) {
                                mPlayer.stop();
                            }

                            mButtonCallback.onStepSelected(mListSteps, indexPosition);

                        } else {
                            Toast.makeText(getActivity(), "You are already on the first step", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });

            btnNext.setOnClickListener(new View.OnClickListener() {

                int id = mListSteps.get(mListIndex).getId();
                int indexPosition = id + 1;

                @Override
                public void onClick(View v) {

                    int lastIndex = mListSteps.size() - 1;

                    if (id < mListSteps.get(lastIndex).getId()) {
                        if (mPlayer != null) {
                            mPlayer.stop();
                        }

                        mButtonCallback.onStepSelected(mListSteps, indexPosition);

                    } else {
                        Toast.makeText(getContext(), "You already are in the Last step of the recipe", Toast.LENGTH_SHORT).show();

                    }
                }

            });

        return fragmentView;
    }


    private void setDetailedStepFragment(List<Steps> listSteps, int index) {

        if (listSteps != null) {

            String description = listSteps.get(index).getDescription();
            tvDescription.setText(description);

            mPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);

            String thumbnailURL = listSteps.get(index).getThumbnailURL();
            String mVideoUrl = listSteps.get(index).getVideoURL();

            if (thumbnailURL != "") {
                Uri builtUri = Uri.parse(thumbnailURL).buildUpon().build();
                Picasso.with(getContext())
                        .load(builtUri)
                        .into(mThumbnailImage);

            }

            if (mVideoUrl != null) {
                if (!mVideoUrl.isEmpty())
                    initializePlayer(Uri.parse(mVideoUrl));
                else {
                    mPlayerView.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.ic_no_video));

                }
            }
        }
    }


    // Initialitze ExoPlayer
    private void initializePlayer(Uri videoUri) {

        if (mPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelection.Factory trackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwithMeter);
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory );
            LoadControl loadControl = new DefaultLoadControl();

            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);

            // Prepare the MediaSource
            String userAgent = Util.getUserAgent(getContext(), "Baking App");
            MediaSource videoSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(
                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);

            mPlayerView.setPlayer(mPlayer);
            mPlayer.prepare(videoSource);
            mPlayer.setPlayWhenReady(true);

        }

    }

    /**
     * Initializes the Media Session to be enabled with media buttons, transport controls, callbacks
     * and media controller.
     */
    private void initializeMediaSession() {

        // Create a MediaSessionCompat.
        mMediaSession = new MediaSessionCompat(getContext(), TAG);

        // Enable callbacks from MediaButtons and TransportControls.
        mMediaSession.setFlags(
                MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS |
                        MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS);

        // Do not let MediaButtons restart the player when the app is not visible.
        mMediaSession.setMediaButtonReceiver(null);

        // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player.
        PlaybackStateCompat.Builder mStateBuilder = new PlaybackStateCompat.Builder()
                .setActions(
                        PlaybackStateCompat.ACTION_PLAY |
                                PlaybackStateCompat.ACTION_PAUSE |
                                PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS |
                                PlaybackStateCompat.ACTION_PLAY_PAUSE);

        mMediaSession.setPlaybackState(mStateBuilder.build());


        // MySessionCallback has methods that handle callbacks from a media controller.
        mMediaSession.setCallback(new MySessionCallback());

        // Start the Media Session since the activity is active.
        mMediaSession.setActive(true);

    }


    private void releasePlayer() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
    }

    // Release the player when activity is destroyed
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mPlayer != null) {
            releasePlayer();
            mMediaSession.setActive(false);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList(Constants.CLICKED_STEP, (ArrayList<? extends Parcelable>) mListSteps);
        currentState.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mListRecipes);
        currentState.putInt("Index", mListIndex);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mButtonCallback = null;
    }


    /**
     * Media Session Callbacks, where all external clients control the player.
     */
    private class MySessionCallback extends MediaSessionCompat.Callback {
        @Override
        public void onPlay() {
            mPlayer.setPlayWhenReady(true);
        }

        @Override
        public void onPause() {
            mPlayer.setPlayWhenReady(false);
        }

        @Override
        public void onSkipToPrevious() {
            mPlayer.seekTo(0);
        }
    }


}
