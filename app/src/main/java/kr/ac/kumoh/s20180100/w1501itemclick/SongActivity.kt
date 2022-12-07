package kr.ac.kumoh.s20180100.w1501itemclick

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.collection.LruCache
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.s20180100.w1501itemclick.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    companion object {
        const val KEY_TITLE = "SongTitle"
        const val KEY_TYPE = "SongType"
        const val KEY_IMAGE = "SongImage"
    }

    private lateinit var binding: ActivitySongBinding
    private lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageLoader = ImageLoader(Volley.newRequestQueue(this),
            object : ImageLoader.ImageCache {
                private val cache = LruCache<String, Bitmap>(20)
                override fun getBitmap(url: String): Bitmap? {
                    return cache.get(url)
                }
                override fun putBitmap(url: String, bitmap: Bitmap) {
                    cache.put(url, bitmap)
                }
            }
        )

        binding.imageSong.setImageUrl(intent.getStringExtra(KEY_IMAGE), imageLoader)
        binding.textTitle.text = intent.getStringExtra(KEY_TITLE)
        binding.textType.text = intent.getStringExtra(KEY_TYPE)
    }
}