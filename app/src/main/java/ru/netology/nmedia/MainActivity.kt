package ru.netology.nmedia

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.counter
import ru.netology.nmedia.models.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                txtTitle.text = post.author;
                txtDate.text = post.published;
                mainTextView.text = post.content;

                textViewLikeCount.text = counter(post.likes);
                textViewShareCount.text = counter(post.shares);
                textViewViewerCount.text = counter(post.views);


                imageViewLike.setImageResource(
                    if (post.likedByMe){
                        R.drawable.ic_liked_24;
                    } else {
                        R.drawable.ic_like_24;
                    }
                )
            }
        }
        binding.imageViewLike.setOnClickListener {
            viewModel.like();
        }
        binding.imageViewShare.setOnClickListener {
            viewModel.share();
        }
    }


}

