package com.example.jwolter.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.R.attr.path;

/**
 * Created by J.Wolter on 10.10.2017.
 */

public interface JsonPlaceholderClient {

    @GET("/posts")
    Call<List<Post>> getPosts ();

    @GET("/posts/{postId}/comments")
    Call<List<Comment>> getCommentByPost (@Path("postId") Integer postId);

}
