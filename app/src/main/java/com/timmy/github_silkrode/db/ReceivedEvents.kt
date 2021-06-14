package com.timmy.github_silkrode.db

import androidx.room.*
import com.google.gson.annotations.SerializedName
import util.fromJson
import util.toJson
import java.io.Serializable

@TypeConverters(ReceivedEventsPersistentConverter::class)
@Entity(tableName = "user_received_events")
data class ReceivedEvent(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "url")
    val url: String = "",

    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String = "",

    @ColumnInfo(name = "login")
    val login: String = "",     // Simple Name

    @ColumnInfo(name = "name")
    val name: String = "",      // Full Name

    @ColumnInfo(name = "location")
    val location: String = "",
):Serializable {
}

data class Actor(
    @SerializedName("id")
    val actorId: Long,
    @SerializedName( "login")
    val login: String,          // Simple Name
//    @SerializedName("name")
//    val name: String = "",      // Full Name
    @SerializedName("url")
    val url: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName( "location")
    val location: String = ""
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