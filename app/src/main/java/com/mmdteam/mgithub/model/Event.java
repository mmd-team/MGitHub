package com.mmdteam.mgithub.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Event {

    public enum EventType {

        CommitCommentEvent,
        CreateEvent,

        DeleteEvent,
        ForkEvent,

        GollumEvent,

        InstallationEvent,

        InstallationRepositoriesEvent,
        IssueCommentEvent,
        IssuesEvent,

        MarketplacePurchaseEvent,

        MemberEvent,

        OrgBlockEvent,

        ProjectCardEvent,

        ProjectColumnEvent,

        ProjectEvent,

        PublicEvent,
        PullRequestEvent,

        PullRequestReviewEvent,
        PullRequestReviewCommentEvent,


        PushEvent,
        ReleaseEvent,
        WatchEvent,

        DeploymentEvent,
        DeploymentStatusEvent,
        MembershipEvent,
        MilestoneEvent,
        OrganizationEvent,
        PageBuildEvent,
        RepositoryEvent,
        StatusEvent,
        TeamEvent,
        TeamAddEvent,
        LabelEvent,

        DownloadEvent,
        FollowEvent,
        ForkApplyEvent,
        GistEvent,

    }

    private String id;
    private EventType type;
    private User actor;
    private User org;
    @SerializedName("public")
    private boolean isPublic;
    @SerializedName("created_at")
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }


    public User getOrg() {
        return org;
    }

    public void setOrg(User org) {
        this.org = org;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
