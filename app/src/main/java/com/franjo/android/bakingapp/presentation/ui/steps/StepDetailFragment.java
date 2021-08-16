//package com.franjo.android.bakingapp.presentation;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Parcelable;
//import androidx.annotation.Nullable;
//import com.google.android.material.snackbar.Snackbar;
//import androidx.fragment.app.Fragment;
//import androidx.core.content.ContextCompat;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.franjo.android.bakingapp.R;
//import com.franjo.android.bakingapp.presentation.model.RecipeUI;
//import com.franjo.android.bakingapp.data.network.model.Steps;
//import com.franjo.android.bakingapp.utilities.Constants;
//import com.google.android.exoplayer2.C;
//import com.google.android.exoplayer2.DefaultLoadControl;
//import com.google.android.exoplayer2.ExoPlayerFactory;
//import com.google.android.exoplayer2.LoadControl;
//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
//import com.google.android.exoplayer2.source.ExtractorMediaSource;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
//import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
//import com.google.android.exoplayer2.trackselection.TrackSelection;
//import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
//import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
//import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
//import com.google.android.exoplayer2.util.Util;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//public class StepDetailFragment extends Fragment {
//
//    private static final String TAG = StepDetailFragment.class.getSimpleName();
//
//    private List<RecipeUI> mListRecipes;
//    private List<Steps> mListSteps;
//
//    private SimpleExoPlayer mPlayer;
//    private DefaultBandwidthMeter bandwithMeter;
//    int currentWindow;
//    private long playbackPosition = C.TIME_UNSET;
//    private static final String CURRENT_POSITION = "current position";
//
//    private int mListIndex;
//    String mRecipeName;
//
//    @BindView(R.id.tvDescription)
//    TextView tvDescription;
//
//    @BindView(R.id.mPlayerView)
//    SimpleExoPlayerView mExoPlayerView;
//
//    @BindView(R.id.thumbImage)
//    ImageView mThumbnailImage;
//
//    @BindView(R.id.btnPrevious)
//    Button btnPrevious;
//
//    @BindView(R.id.btnNext)
//    Button btnNext;
//
//    // Define a new interface OnStepDetailListener that triggers a callback in the host activity
//    OnStepDetailListener mButtonCallback;
//
//    View fragmentView;
//
//
//    // Mandatory empty constructor
//    public StepDetailFragment() {
//
//    }
//
//
//
//
//    public interface OnStepDetailListener {
//        void onStepSelected(List<Steps> stepsList, int position);
//    }
//
//    // Override onAttach to make sure that the container activity has implemented the callback
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        // This makes sure that the host activity has implemented the callback interface
//        // If not, it throws an exception
//        try {
//            mButtonCallback = (OnStepDetailListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnStepDetailListener");
//        }
//    }
//
//
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mListRecipes = new ArrayList<>();
//        mListSteps = new ArrayList<>();
//        bandwithMeter = new DefaultBandwidthMeter();
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        Bundle bundle = getArguments();
//        mButtonCallback = (RecipeDetailActivity)getActivity();
//
//        mRecipeName = ((RecipeDetailActivity)getActivity()).mRecipeName;
//
//        if (savedInstanceState != null) {
//            mListRecipes = savedInstanceState.getParcelableArrayList(Constants.CLICKED_RECIPE);
//            mListSteps = savedInstanceState.getParcelableArrayList(Constants.CLICKED_STEP);
//            mListIndex = savedInstanceState.getInt("Index");
//            playbackPosition = savedInstanceState.getLong(CURRENT_POSITION, C.TIME_UNSET);
//
//        } else {
//            mListSteps = bundle.getParcelableArrayList(Constants.CLICKED_STEP);
//            if(mListSteps != null) {
//                mListSteps = bundle.getParcelableArrayList(Constants.CLICKED_STEP);
//                mListIndex = bundle.getInt("Index");
//
//            } else {
//                mListRecipes = bundle.getParcelableArrayList(Constants.CLICKED_RECIPE);
//                if (mListRecipes != null) {
//                    mListSteps = mListRecipes.get(0).getSteps();
//                    mListIndex = 0;
//                }
//
//            }
//        }
//
//
//        // Inflate the layout for this fragment
//        fragmentView = inflater.inflate(R.layout.fragment_step_detail, container, false);
//
//        ButterKnife.bind(this, fragmentView);
//
//        setDetailedStepFragment(mListSteps, mListIndex);
//
//
//
//            btnPrevious.setOnClickListener(new View.OnClickListener() {
//
//                int id = mListSteps.get(mListIndex).getId();
//                int indexPosition = id - 1;
//
//                @Override
//                public void onClick(View view) {
//
//                    if (mListSteps != null) {
//                        if (id > 0) {
//                            if (mPlayer != null) {
//                                mPlayer.stop();
//                            }
//
//                            mButtonCallback.onStepSelected(mListSteps, indexPosition);
//
//                        } else {
//                            Snackbar.make(view, R.string.button_previous_text_end, Snackbar.LENGTH_LONG).show();
//
//                        }
//                    }
//                }
//            });
//
//            btnNext.setOnClickListener(new View.OnClickListener() {
//
//                int id = mListSteps.get(mListIndex).getId();
//                int indexPosition = id + 1;
//
//                @Override
//                public void onClick(View view) {
//
//                    int lastIndex = mListSteps.size() - 1;
//
//                    if (id < mListSteps.get(lastIndex).getId()) {
//                        if (mPlayer != null) {
//                            mPlayer.stop();
//                        }
//
//                        mButtonCallback.onStepSelected(mListSteps, indexPosition);
//
//                    } else {
//                        Snackbar.make(view, R.string.button_next_end_text, Snackbar.LENGTH_LONG).show();
//                    }
//                }
//
//            });
//
//
//
//        return fragmentView;
//    }
//
//
//    private void setDetailedStepFragment(List<Steps> listSteps, int index) {
//
//        if (listSteps != null) {
//
//            String description = listSteps.get(index).getDescription();
//            tvDescription.setText(description);
//
//            mExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
//
//            String thumbnailURL = listSteps.get(index).getThumbnailURL();
//            String mVideoUrl = listSteps.get(index).getVideoURL();
//
//            if (TextUtils.isEmpty(thumbnailURL)) {
//                Uri builtUri = Uri.parse(thumbnailURL).buildUpon().build();
//                Picasso.with(getContext())
//                        .load(builtUri)
//                        .into(mThumbnailImage);
//
//            }
//
//            if (mVideoUrl != null) {
//                if (!mVideoUrl.isEmpty())
//                    initializePlayer(Uri.parse(mVideoUrl));
//                else {
//                    mExoPlayerView.setForeground(ContextCompat.getDrawable(getContext(), R.drawable.ic_no_video));
//
//                }
//            }
//        }
//    }
//
//
//    // Initialitze ExoPlayer
//    private void initializePlayer(Uri videoUri) {
//
//        if (mPlayer == null) {
//            // Create an instance of the ExoPlayer.
//            TrackSelection.Factory trackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwithMeter);
//            DefaultTrackSelector trackSelector = new DefaultTrackSelector(trackSelectionFactory );
//            LoadControl loadControl = new DefaultLoadControl();
//
//            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
//
//            // Prepare the MediaSource
//            String userAgent = Util.getUserAgent(getContext(), "Baking app");
//            MediaSource videoSource = new ExtractorMediaSource(videoUri, new DefaultDataSourceFactory(
//                    getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
//
//            mExoPlayerView.setPlayer(mPlayer);
//            mPlayer.prepare(videoSource);
//            mPlayer.setPlayWhenReady(true);
//            mPlayer.seekTo(currentWindow, playbackPosition);
//
//        }
//        if (fragmentView.findViewWithTag("fragment_step_landscape") != null) {
//            hideSystemUi();
//        }
//
//    }
//
//    private void releasePlayer() {
//        mPlayer.stop();
//        mPlayer.release();
//        mPlayer = null;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mButtonCallback = null;
//        if (mPlayer != null) {
//            mPlayer.stop();
//            mPlayer.release();
//        }
//    }
//
//    // Release the player when activity is destroyed
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        if(mPlayer != null) {
//            releasePlayer();
//        }
//
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mPlayer!=null) {
//            mPlayer.stop();
//            mPlayer.release();
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (mPlayer!=null) {
//            mPlayer.stop();
//            playbackPosition = mPlayer.getCurrentPosition();
//            mPlayer.release();
//        }
//    }
//
//    @SuppressLint("InlinedApi")
//    private void hideSystemUi() {
//        mExoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle currentState) {
//        super.onSaveInstanceState(currentState);
//        currentState.putParcelableArrayList(Constants.CLICKED_STEP, (ArrayList<? extends Parcelable>) mListSteps);
//        currentState.putParcelableArrayList(Constants.CLICKED_RECIPE, (ArrayList<? extends Parcelable>) mListRecipes);
//        currentState.putInt("Index", mListIndex);
//        currentState.putLong(CURRENT_POSITION, playbackPosition);
//
//    }
//
//}
