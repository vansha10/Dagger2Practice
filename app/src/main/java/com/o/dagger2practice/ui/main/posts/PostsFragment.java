package com.o.dagger2practice.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o.dagger2practice.R;
import com.o.dagger2practice.utils.VerticalSpaceItemDecorator;
import com.o.dagger2practice.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";

    private PostsViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        initRecyclerView();

        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), postListResource -> {
            if (postListResource != null) {
                Log.d(TAG, "subscribeObservers: " + postListResource.data);
                switch (postListResource.status) {
                    case LOADING:
                        Log.d(TAG, "subscribeObservers: loading...");
                        break;
                    case SUCCESS:
                        Log.d(TAG, "subscribeObservers: posts retrieved");
                        postsRecyclerAdapter.setPosts(postListResource.data);
                        break;
                    case ERROR:
                        Log.e(TAG, "subscribeObservers: error: " + postListResource.message);
                        break;
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecorator itemDecorator = new VerticalSpaceItemDecorator(15);
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(postsRecyclerAdapter);
    }
}
