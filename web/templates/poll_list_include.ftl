<#if (poll?size>0)>
<#list poll as pollDB>
<div class="col-md-4 blog-item-wrapper d-flex flex-column">
    <div class="blog-item d-flex flex-column flex-grow-1">
        <div class="blog-img">
            <#if (pollDB.pollstatus)>
            <a href="/rispondisondaggio"><img src="../style/img/card-image.png" alt=""></a>
            <#else>
            <a href="#"><img src="../style/img/card-image.png" alt=""></a>
        </#if>
        </div>
        <div class="blog-text">
            <div class="blog-tag">
                <#if (pollDB.pollstatus)>
                <a href="#"><h6><small>IN CORSO</small></h6></a>
                <#else>
                <a href="#"><h6><small>CHIUSO</small></h6></a>
            </#if>
            </div>
        <div class="blog-title">
            <a href="#"><h4>${pollDB.title}</h4></a>
        </div>
        <div class="blog-meta">
            <p class="blog-date">30 May 2016</p> /
            <p class="blog-comment"><a href="">23 Comments</a></p>
        </div>
        <div class="blog-desc">
            <p>${pollDB.opentext}</p>
        </div>
        <div class="blog-author">
            <p>by "${pollDB.user.name}"</p>
        </div>
        <div class="blog-share-wrapper">
            <a class="blog-share" href="google.com">
                <i class="fab fa-facebook-square"></i>
            </a>
            <a class="blog-share" href="google.com">
                <i class="fab fa-twitter-square"></i>
            </a>
        </div>
        </div>
    </div>
</div>
<!-- End of Blog Item -->
</#list>
<#else>
<p>Non sono presenti sondaggi nel database</p>
</#if>