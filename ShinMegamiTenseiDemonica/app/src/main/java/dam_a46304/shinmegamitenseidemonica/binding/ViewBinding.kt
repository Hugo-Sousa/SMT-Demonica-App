package dam_a46304.shinmegamitenseidemonica.binding

import android.view.View
import android.view.WindowManager
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import com.skydoves.progressview.ProgressView
import com.skydoves.rainbow.Rainbow
import com.skydoves.rainbow.RainbowOrientation
import com.skydoves.rainbow.color

object ViewBinding {

    // Change background color in Card in Pokemon List
    @JvmStatic
    @BindingAdapter("paletteImage", "paletteCard")
    fun bindLoadImagePalette (view : AppCompatImageView, url : String?, paletteCard : MaterialCardView) {
        if( url != null )
        {
            Glide . with ( view . context )
                . load ( url )
                . listener (
                    GlidePalette . with ( url )
                        . use ( BitmapPalette . Profile . MUTED_LIGHT )
                        . intoCallBack { palette ->
                            val rgb = palette ?. dominantSwatch ?. rgb
                            if ( rgb != null ) {
                                paletteCard . setCardBackgroundColor ( rgb )
                            }
                        }. crossfade ( true )
                ). into ( view )
        }
    }

    @JvmStatic
    @BindingAdapter("paletteImage")
    fun bindLoadImagePalette (view : AppCompatImageView, url : String?) {
        if( url != null )
        {
            Glide . with ( view . context )
                . load ( url )
                . listener (
                    GlidePalette . with ( url )
                        . use ( BitmapPalette . Profile . MUTED_LIGHT )
                ). into ( view )
        }
    }

    // Change background color in Pokemon Detail
    @JvmStatic
    @BindingAdapter("paletteImage", "paletteView")
    fun bindLoadImagePaletteView (view : AppCompatImageView, url : String, paletteView : View) {
        val context = view . context
        if( url != null ) {
            Glide.with ( context )
                .load ( url )
                .listener (
                    GlidePalette.with( url )
                        .use( BitmapPalette.Profile.MUTED_LIGHT )
                        .intoCallBack { palette ->
                            val light = palette ?. lightVibrantSwatch ?. rgb
                            val domain = palette ?. dominantSwatch ?. rgb
                            if ( domain != null ) {
                                if ( light != null ) {
                                    Rainbow( paletteView ).palette {
                                        + color ( domain )
                                        + color ( light )
                                    }. background ( orientation = RainbowOrientation . TOP_BOTTOM )
                                } else {
                                    paletteView . setBackgroundColor ( domain )
                                }
                                if ( context is AppCompatActivity) {
                                    context . window . apply {
                                        addFlags ( WindowManager . LayoutParams . FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS )
                                        statusBarColor = domain
                                    }
                                }
                            }
                        }.crossfade ( true )
                ). into ( view )
        }
    }

    // add back behaviour in image
    @JvmStatic
    @BindingAdapter("onBackPressed")
    fun bindOnBackPressed (view : View, onBackPress : Boolean ) {
        val context = view . context
        if ( onBackPress && context is OnBackPressedDispatcherOwner) {
            view . setOnClickListener {
                context.onBackPressedDispatcher.onBackPressed ()
            }
        }
    }
    // convert int to float
    @JvmStatic
    @BindingAdapter("progressView_labelText")
    fun bindProgressViewLabelText (progressView : ProgressView, text : String ?) {
        progressView.labelText = text
    }
    // convert int to float
    @JvmStatic
    @BindingAdapter("progressView_progress")
    fun bindProgressViewProgress (progressView : ProgressView, value : Int ?) {
        if ( value != null ) {
            progressView.progress = value.toFloat()
        }
    }
    // convert int to float
    @JvmStatic
    @BindingAdapter("progressView_max")
    fun bindProgressViewMax (progressView : ProgressView, value : Int ?) {
        if ( value != null ) {
            progressView.max = value.toFloat()
        }
    }

}