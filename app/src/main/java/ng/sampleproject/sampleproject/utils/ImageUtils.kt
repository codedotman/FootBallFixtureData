package ng.sampleproject.sampleproject.utils

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import ng.sampleproject.sampleproject.R
import ng.sampleproject.sampleproject.utils.glide.GlideApp
import ng.sampleproject.sampleproject.utils.glide.SvgSoftwareLayerSetter

/**
 * Created by USER on 10/08/2019.
 */
object ImageUtils {

    fun loadImage(imageURL: String, view: ImageView) {

        if (imageURL.endsWith(".svg")) {
            GlideApp.with(view.context)
                    .`as`<PictureDrawable>(PictureDrawable::class.java)
                    .error(R.mipmap.ic_launcher_round)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .listener(SvgSoftwareLayerSetter())
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
                    .load(Uri.parse(imageURL))
                    .override(64, 64)
                    .into(view)
        } else {

            GlideApp.with(view.context)
                    .load(imageURL)
                    .error(R.mipmap.ic_launcher_round)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view)

        }

    }
}