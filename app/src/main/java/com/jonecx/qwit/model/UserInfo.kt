package com.jonecx.qwit.model

import com.squareup.moshi.Json

data class UserInfo(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "id_str")
    val idStr: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "screen_name")
    val screenName: String,
    @field:Json(name = "location")
    val location: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "entities")
    val entities: Entities,
    @field:Json(name = "protected")
    val `protected`: Boolean,
    @field:Json(name = "followers_count")
    val followersCount: Int,
    @field:Json(name = "friends_count")
    val friendsCount: Int,
    @field:Json(name = "listed_count")
    val listedCount: Int,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "favourites_count")
    val favouritesCount: Int,
    @field:Json(name = "utc_offset")
    val utcOffset: Any,
    @field:Json(name = "time_zone")
    val timeZone: Any,
    @field:Json(name = "geo_enabled")
    val geoEnabled: Boolean,
    @field:Json(name = "verified")
    val verified: Boolean,
    @field:Json(name = "statuses_count")
    val statusesCount: Int,
    @field:Json(name = "lang")
    val lang: Any,
    @field:Json(name = "status")
    val status: Status,
    @field:Json(name = "contributors_enabled")
    val contributorsEnabled: Boolean,
    @field:Json(name = "is_translator")
    val isTranslator: Boolean,
    @field:Json(name = "is_translation_enabled")
    val isTranslationEnabled: Boolean,
    @field:Json(name = "profile_background_color")
    val profileBackgroundColor: String,
    @field:Json(name = "profile_background_image_url")
    val profileBackgroundImageUrl: String,
    @field:Json(name = "profile_background_image_url_https")
    val profileBackgroundImageUrlHttps: String,
    @field:Json(name = "profile_background_tile")
    val profileBackgroundTile: Boolean,
    @field:Json(name = "profile_image_url")
    val profileImageUrl: String,
    @field:Json(name = "profile_image_url_https")
    val profileImageUrlHttps: String,
    @field:Json(name = "profile_banner_url")
    val profileBannerUrl: String,
    @field:Json(name = "profile_link_color")
    val profileLinkColor: String,
    @field:Json(name = "profile_sidebar_border_color")
    val profileSidebarBorderColor: String,
    @field:Json(name = "profile_sidebar_fill_color")
    val profileSidebarFillColor: String,
    @field:Json(name = "profile_text_color")
    val profileTextColor: String,
    @field:Json(name = "profile_use_background_image")
    val profileUseBackgroundImage: Boolean,
    @field:Json(name = "has_extended_profile")
    val hasExtendedProfile: Boolean,
    @field:Json(name = "default_profile")
    val defaultProfile: Boolean,
    @field:Json(name = "default_profile_image")
    val defaultProfileImage: Boolean,
    @field:Json(name = "following")
    val following: Boolean,
    @field:Json(name = "follow_request_sent")
    val followRequestSent: Boolean,
    @field:Json(name = "notifications")
    val notifications: Boolean,
    @field:Json(name = "translator_type")
    val translatorType: String,
    @field:Json(name = "withheld_in_countries")
    val withheldInCountries: List<Any>,
    @field:Json(name = "suspended")
    val suspended: Boolean,
    @field:Json(name = "needs_phone_verification")
    val needsPhoneVerification: Boolean
) {
/*    data class Entities(
        @field:Json(name= "description")
        val description: Description
    ) {
        data class Description(
            @field:Json(name= "urls")
            val urls: List<Any>
        )
    }*/

    data class Status(
        @field:Json(name = "created_at")
        val createdAt: String,
        @field:Json(name = "id")
        val id: Long,
        @field:Json(name = "id_str")
        val idStr: String,
        @field:Json(name = "text")
        val text: String,
        @field:Json(name = "truncated")
        val truncated: Boolean,
        @field:Json(name = "entities")
        val entities: Entities,
        @field:Json(name = "source")
        val source: String,
        @field:Json(name = "in_reply_to_status_id")
        val inReplyToStatusId: Any,
        @field:Json(name = "in_reply_to_status_id_str")
        val inReplyToStatusIdStr: Any,
        @field:Json(name = "in_reply_to_user_id")
        val inReplyToUserId: Any,
        @field:Json(name = "in_reply_to_user_id_str")
        val inReplyToUserIdStr: Any,
        @field:Json(name = "in_reply_to_screen_name")
        val inReplyToScreenName: Any,
        @field:Json(name = "geo")
        val geo: Any,
        @field:Json(name = "coordinates")
        val coordinates: Any,
        @field:Json(name = "place")
        val place: Any,
        @field:Json(name = "contributors")
        val contributors: Any,
        @field:Json(name = "retweeted_status")
        val retweetedStatus: RetweetedStatus,
        @field:Json(name = "is_quote_status")
        val isQuoteStatus: Boolean,
        @field:Json(name = "retweet_count")
        val retweetCount: Int,
        @field:Json(name = "favorite_count")
        val favoriteCount: Int,
        @field:Json(name = "favorited")
        val favorited: Boolean,
        @field:Json(name = "retweeted")
        val retweeted: Boolean,
        @field:Json(name = "lang")
        val lang: String
    ) {
        data class Entities(
            @field:Json(name = "hashtags")
            val hashtags: List<Hashtag>,
            @field:Json(name = "symbols")
            val symbols: List<Any>,
            @field:Json(name = "user_mentions")
            val userMentions: List<UserMention>,
            @field:Json(name = "urls")
            val urls: List<Any>
        ) {
            data class Hashtag(
                @field:Json(name = "text")
                val text: String,
                @field:Json(name = "indices")
                val indices: List<Int>
            )

            data class UserMention(
                @field:Json(name = "screen_name")
                val screenName: String,
                @field:Json(name = "name")
                val name: String,
                @field:Json(name = "id")
                val id: Long,
                @field:Json(name = "id_str")
                val idStr: String,
                @field:Json(name = "indices")
                val indices: List<Int>
            )
        }

        data class RetweetedStatus(
            @field:Json(name = "created_at")
            val createdAt: String,
            @field:Json(name = "id")
            val id: Long,
            @field:Json(name = "id_str")
            val idStr: String,
            @field:Json(name = "text")
            val text: String,
            @field:Json(name = "truncated")
            val truncated: Boolean,
            @field:Json(name = "entities")
            val entities: Entities,
            @field:Json(name = "source")
            val source: String,
            @field:Json(name = "in_reply_to_status_id")
            val inReplyToStatusId: Any,
            @field:Json(name = "in_reply_to_status_id_str")
            val inReplyToStatusIdStr: Any,
            @field:Json(name = "in_reply_to_user_id")
            val inReplyToUserId: Any,
            @field:Json(name = "in_reply_to_user_id_str")
            val inReplyToUserIdStr: Any,
            @field:Json(name = "in_reply_to_screen_name")
            val inReplyToScreenName: Any,
            @field:Json(name = "geo")
            val geo: Any,
            @field:Json(name = "coordinates")
            val coordinates: Any,
            @field:Json(name = "place")
            val place: Any,
            @field:Json(name = "contributors")
            val contributors: Any,
            @field:Json(name = "is_quote_status")
            val isQuoteStatus: Boolean,
            @field:Json(name = "retweet_count")
            val retweetCount: Int,
            @field:Json(name = "favorite_count")
            val favoriteCount: Int,
            @field:Json(name = "favorited")
            val favorited: Boolean,
            @field:Json(name = "retweeted")
            val retweeted: Boolean,
            @field:Json(name = "possibly_sensitive")
            val possiblySensitive: Boolean,
            @field:Json(name = "lang")
            val lang: String
        ) {
            data class Entities(
                @field:Json(name = "hashtags")
                val hashtags: List<Hashtag>,
                @field:Json(name = "symbols")
                val symbols: List<Any>,
                @field:Json(name = "user_mentions")
                val userMentions: List<Any>,
                @field:Json(name = "urls")
                val urls: List<Url>
            ) {
                data class Hashtag(
                    @field:Json(name = "text")
                    val text: String,
                    @field:Json(name = "indices")
                    val indices: List<Int>
                )

                data class Url(
                    @field:Json(name = "url")
                    val url: String,
                    @field:Json(name = "expanded_url")
                    val expandedUrl: String,
                    @field:Json(name = "display_url")
                    val displayUrl: String,
                    @field:Json(name = "indices")
                    val indices: List<Int>
                )
            }
        }
    }
}
