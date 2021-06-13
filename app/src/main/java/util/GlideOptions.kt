package util

import com.bumptech.glide.Priority
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.timmy.github_silkrode.R

/**Glide圓角圖片設定*/
val options by lazy {
    RequestOptions()
        .transform(MultiTransformation(CenterCrop(), CircleCrop()))
        .priority(Priority.NORMAL)
        .error(R.drawable.ic_github_avatar_default)
//            .diskCacheStrategy(DiskCacheStrategy.NONE)
//            .skipMemoryCache(true)
}