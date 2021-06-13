package com.timmy.github_silkrode.db

import androidx.room.*
import com.google.gson.annotations.SerializedName
import util.fromJson
import util.toJson

@TypeConverters(ReceivedEventsPersistentConverter::class)
@Entity(tableName = "user_received_events")
data class ReceivedEvent(
    @PrimaryKey
    val id: Long = 0,

    @ColumnInfo(name = "url")
    val url: String = "",

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String = "",

    @ColumnInfo(name = "login")
    val login: String = "", // Simple Name

    @ColumnInfo(name = "name")
    val name: String = "", // Full Name

    @ColumnInfo(name ="location")
    val location: String = "",
) {
    var indexInResponse: Int = -1
}
data class aaa(
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("bio")
    val bio: String = "",
    @SerializedName("blog")
    val blog: String = "",
    @SerializedName("company")
    val company: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("events_url")
    val eventsUrl: String = "",
    @SerializedName("followers")
    val followers: Int = 0,
    @SerializedName("followers_url")
    val followersUrl: String = "",
    @SerializedName("following")
    val following: Int = 0,
    @SerializedName("following_url")
    val followingUrl: String = "",
    @SerializedName("gists_url")
    val gistsUrl: String = "",
    @SerializedName("gravatar_id")
    val gravatarId: String = "",
    @SerializedName("hireable")
    val hireable: Boolean = false,
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("id")
    val id: Long = 0,


    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("organizations_url")
    val organizationsUrl: String = "",
    @SerializedName("public_gists")
    val publicGists: Int = 0,
    @SerializedName("public_repos")
    val publicRepos: Int = 0,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String = "",
    @SerializedName("repos_url")
    val reposUrl: String = "",
    @SerializedName("site_admin")
    val siteAdmin: Boolean = false,
    @SerializedName("starred_url")
    val starredUrl: String = "",
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String = "",
    @SerializedName("twitter_username")
    val twitterUsername: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = "",
    @SerializedName("url")
    val url: String = ""
)
data class Actor(
    @SerializedName("id")
    val actorId: Int,
    val login: String,
    @SerializedName("display_login")
    val displayLogin: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    val url: String,
    @SerializedName("avatar_url")
    val avatarUrl: String
)

data class ReceivedEventRepo(
    @SerializedName("id")
    val repoId: String,
    val name: String,
    val url: String
)

enum class Type {
    WatchEvent,
    ForkEvent,
    PushEvent,
    CreateEvent,
    MemberEvent,
    PublicEvent,
    IssuesEvent,
    IssueCommentEvent,
    CheckRunEvent,
    CheckSuiteEvent,
    CommitCommentEvent,
    DeleteEvent,
    DeploymentEvent,
    DeploymentStatusEvent,
    DownloadEvent,
    FollowEvent,
    ForkApplyEvent,
    GitHubAppAuthorizationEvent,
    GistEvent,
    GollumEvent,
    InstallationEvent,
    InstallationRepositoriesEvent,
    MarketplacePurchaseEvent,
    LabelEvent,
    MembershipEvent,
    MilestoneEvent,
    OrganizationEvent,
    OrgBlockEvent,
    PageBuildEvent,
    ProjectCardEvent,
    ProjectColumnEvent,
    ProjectEvent,
    PullRequestEvent,
    PullRequestReviewEvent,
    PullRequestReviewCommentEvent,
    ReleaseEvent,
    RepositoryEvent,
    RepositoryImportEvent,
    RepositoryVulnerabilityAlertEvent,
    SecurityAdvisoryEvent,
    StatusEvent,
    TeamEvent,
    TeamAddEvent
}

val SUPPORT_EVENT_TYPES: List<Type> = listOf(
    Type.WatchEvent,
    Type.ForkEvent,
    Type.PushEvent,
    Type.CreateEvent
)

class ReceivedEventsPersistentConverter {

    // Actor
    @TypeConverter
    fun storeActorToString(data: Actor): String = data.toJson()

    @TypeConverter
    fun storeStringToActor(value: String): Actor = value.fromJson()

    // ReceivedEventRepo
    @TypeConverter
    fun storeRepoToString(data: ReceivedEventRepo): String = data.toJson()

    @TypeConverter
    fun storeStringToRepo(value: String): ReceivedEventRepo = value.fromJson()

    // Type
    @TypeConverter
    fun restoreEnum(enumName: String): Type = Type.valueOf(enumName)

    @TypeConverter
    fun saveEnumToString(enumType: Type) = enumType.name
}