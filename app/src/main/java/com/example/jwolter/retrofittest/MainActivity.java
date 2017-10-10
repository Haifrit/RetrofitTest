package com.example.jwolter.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        JsonPlaceholderClient client =  retrofit.create(JsonPlaceholderClient.class);

        Call<List<Post>> call =  client.getPosts();
        Call<List<Comment>> call2 = client.getCommentByPost(1);

        call2.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> comments = response.body();
                Log.d("MAIN","Number of Comments retreived = " + comments.size());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d("MAIN","Failure");
            }
        });

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                Log.d("MAIN","Number of Posts retreived = " + posts.size());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("MAIN","Failure");
            }
        });

    }
}
