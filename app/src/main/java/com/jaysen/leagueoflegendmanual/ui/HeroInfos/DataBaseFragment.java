package com.jaysen.leagueoflegendmanual.ui.HeroInfos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Preconditions;
import com.jaysen.leagueoflegendmanual.ui.APP;
import com.jaysen.leagueoflegendmanual.pattern.mvp.Presenter;
import com.jaysen.leagueoflegendmanual.R;
import com.jaysen.leagueoflegendmanual.dagger.DataModule;
import com.jaysen.leagueoflegendmanual.pattern.clean.domain.model.HeroEntity;

import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataBaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DataBaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataBaseFragment extends Fragment implements Presenter.View<List<HeroEntity>> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DataBaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataBaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataBaseFragment newInstance(String param1, String param2) {
        DataBaseFragment fragment = new DataBaseFragment();
        Bundle           args     = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_base, container, false);
    }

    @Inject
    HeroFragmentPresenter mHeroFragmentPresenter;

    @Override

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        APP app = (APP) getActivity().getApplication();
        app.getApplicationComponent().getDataSourceBuilder().dataModule(new DataModule()).build().inject(this);
        Preconditions.checkNotNull(mHeroFragmentPresenter);
        mHeroFragmentPresenter.setmView(this);
        mHeroFragmentPresenter.loadData();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onLoadSuccess(List<HeroEntity> data) {
        Preconditions.checkNotNull(getView());
        ((TextView) getView().findViewById(R.id.textView)).setText(data.toString());

    }

    @Override
    public void onLoadFailed() {
        Preconditions.checkNotNull(getView());
        ((TextView) getView().findViewById(R.id.textView)).setText("onLoadFailed");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
