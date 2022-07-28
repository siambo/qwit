package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "id")
    val id: Long,
    @Json(name = "id_str")
    val idStr: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "screen_name")
    val screenName: String,
    @Json(name = "location")
    val location: String?,
    @Json(name = "derived")
    val derived: List<Location>?,
    @Json(name = "entities")
    val userEntities: UserEntity?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "protected")
    val isProtected: Boolean,
    @Json(name = "verified")
    val isVerified: Boolean,
    @Json(name = "followers_count")
    val followersCount: Int,
    @Json(name = "friends_count")
    val friendsCount: Int,
    @Json(name = "listed_count")
    val listedCount: Int,
    @Json(name = "favourites_count")
    val favoritesCount: Int,
    @Json(name = "statuses_count")
    val statusCount: Int,
    @Json(name = "status")
    val tweet: Tweet?,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "has_extended_profile")
    val hasExtendedProfile: Boolean,
    @Json(name = "profile_banner_url")
    val profileBannerUrl: String,
    @Json(name = "profile_image_url_https")
    val profileImageUrlHttps: String,
    @Json(name = "default_profile")
    val hasDefaultProfile: Boolean,
    @Json(name = "default_profile_image")
    val hasDefaultProfileImage: Boolean,
    @Json(name = "profile_background_color")
    val profileBackgroundColor: String,
    @Json(name = "profile_background_image_url")
    val profileBackgroundImageUrl: String,
    @Json(name = "profile_background_image_url_https")
    val profileBackgroundImageUrlHttps: String,
    @Json(name = "profile_background_tile")
    val isProfileBackgroundTiled: Boolean,
    @Json(name = "profile_image_url")
    val profileImageUrl: String,
    @Json(name = "profile_link_color")
    val profileLinkColor: String,
    @Json(name = "profile_sidebar_border_color")
    val profileSidebarBorderColor: String,
    @Json(name = "profile_sidebar_fill_color")
    val profileSidebarFillColor: String,
    @Json(name = "profile_text_color")
    val profileTextColor: String,
    @Json(name = "profile_use_background_image")
    val doesProfileUseBackgroundImage: Boolean,
    @Json(name = "following")
    val isFollowing: Boolean,
    @Json(name = "follow_request_sent")
    val isFollowRequestSent: Boolean,
    @Json(name = "notifications")
    val notifications: Boolean,
    @Json(name = "translator_type")
    val translatorType: String,
    @Json(name = "withheld_in_countries")
    val withheldInCountries: List<String>,
    @Json(name = "suspended")
    val isSuspended: Boolean,
    @Json(name = "needs_phone_verification")
    val needsPhoneVerification: Boolean,
    @Json(name = "contributors_enabled")
    val areContributorsEnabled: Boolean,
    @Json(name = "is_translator")
    val isTranslator: Boolean,
    @Json(name = "is_translation_enabled")
    val isTranslatorEnabled: Boolean,
)