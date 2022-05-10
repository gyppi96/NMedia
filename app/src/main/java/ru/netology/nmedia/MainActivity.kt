package ru.netology.nmedia

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import ru.netology.nmedia.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    lateinit var imageViewLike: ImageView;
    lateinit var imageViewShare: ImageView;
    lateinit var txtShareCount: TextView;
    lateinit var txtLikeCount: TextView;
    lateinit var txtViewCount: TextView;

    var shareCount = 9999;
    var likeCount = 999999;
    var viewCount = 0;

    private var likeDoubleTapFlag = 0;
    private var shareDoubleTapFlag = 0;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        imageViewLike = findViewById<ImageView>(R.id.imageViewLike); // кнопка для лайка
        imageViewShare = findViewById<ImageView>(R.id.imageViewShare); // кнопка для отправки
        txtShareCount = findViewById<TextView>(R.id.textViewShareCount); // показывет количество share
        txtLikeCount = findViewById<TextView>(R.id.textViewLikeCount); // показывет количество like
        txtViewCount = findViewById<TextView>(R.id.textViewViewerCount); // показывет количество share
        val imgLogo = findViewById<AppCompatImageView>(R.id.logo);

        txtLikeCount.setText(likeCount.toString());
        txtShareCount.setText(shareCount.toString());
        txtViewCount.setText(viewCount.toString());

        //imageViewLike.setOnClickListener(onClickListenerForImageViewLike);
        //imageViewShare.setOnClickListener(onClickListenerForImageViewShare);

        binding.root.setOnClickListener(View.OnClickListener {
            System.out.println("in binding root");
        })

        binding.imageViewLike.setOnClickListener(onClickListenerForImageViewLike);
        binding.imageViewShare.setOnClickListener(onClickListenerForImageViewShare);
        binding.logo.setOnClickListener(View.OnClickListener {
            System.out.println("in binding logo");
        })

    }

    // onClickListener для нажатия на кнопку Like
    private val onClickListenerForImageViewLike = View.OnClickListener { view ->
        if(likeDoubleTapFlag == 0){
            // одно нажатие - ставим like
            likeDoubleTapFlag = 1;

            likeCount++;
            txtLikeCount.setText(counter(likeCount));

            imageViewLike.setImageDrawable(ContextCompat.getDrawable(applicationContext,android.R.drawable.btn_star_big_on));

        }else if (likeDoubleTapFlag == 1){
            // второе нажатие - убираем like
            likeDoubleTapFlag = 0;

            likeCount--;
            txtLikeCount.setText(counter(likeCount));

            imageViewLike.setImageDrawable(ContextCompat.getDrawable(applicationContext,android.R.drawable.btn_star_big_off));

        }
    }

    // onClickListener для нажатия на кнопку Share
    private val onClickListenerForImageViewShare = View.OnClickListener { view ->
        if(shareDoubleTapFlag == 0){
            // одно нажатие - ставим share
            shareDoubleTapFlag = 1;

            shareCount += 10;
            txtShareCount.setText(counter(shareCount));

            imageViewShare.setBackgroundColor(Color.GRAY);

        }else if (shareDoubleTapFlag == 1){
            // второе нажатие - убираем share
            shareDoubleTapFlag = 0;

            shareCount -= 10;
            txtShareCount.setText(counter(shareCount));

            imageViewShare.setBackgroundColor(Color.TRANSPARENT);

        }

    }

    // функция рассчета количества и изменения порядка
    fun counter(item: Int): String {
        return when (item) {
            in 1000..1099 -> {
                val num = roundOffDecimal(item / 1000.0)
                (num + "K")
            }
            in 1100..9999 -> {
                val num = roundOffDecimal(item / 1000.0)
                (num + "K")
            }
            in 10_000..999_999 -> {
                ((item / 1000).toString() + "K")
            }
            in 1_000_000..1_000_000_000 -> {
                val num = roundOffDecimal(item / 1_000_000.0)
                (num + "M")
            }
            else -> item.toString()
        }
    }

    private fun roundOffDecimal(number: Double): String {
        val decimalFormat = DecimalFormat("#.#")
        decimalFormat.roundingMode = RoundingMode.FLOOR
        return decimalFormat.format(number).toString()
    }




// end
}
