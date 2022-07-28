package com.jonecx.qwit.model.media

import com.jonecx.qwit.model.media.Resize.Crop
import com.jonecx.qwit.model.media.Resize.Fit

enum class MediaSize(h: Int, resize: Resize, w: Int) {
    Thumb(150, Crop, 150),
    Large(454, Fit, 680),
    Medium(800, Fit, 1200),
    Small(1366, Fit, 2048)
}
