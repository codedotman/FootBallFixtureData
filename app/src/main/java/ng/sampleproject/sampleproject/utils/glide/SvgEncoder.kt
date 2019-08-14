package ng.sampleproject.sampleproject.utils.glide

import com.bumptech.glide.load.EncodeStrategy
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceEncoder
import com.bumptech.glide.load.engine.Resource
import com.caverock.androidsvg.SVG
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

/**
 * Created by USER on 11/08/2019.
 */
class SvgEncoder : ResourceEncoder<SVG> {
    override fun getEncodeStrategy(options: Options): EncodeStrategy {
        return EncodeStrategy.SOURCE
    }

    override fun encode(data: Resource<SVG>, file: File, options: Options): Boolean {
        try {
            val svg = data.get()
            val picture = svg.renderToPicture()
            picture.writeToStream(FileOutputStream(file))
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }
}