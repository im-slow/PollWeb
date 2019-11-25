<section id="gtco-contact-form" class="bg-white pt-5">
    <div class="container">
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <h2 class="section-title">Benvenuto, ${newUser.name}</h2>
                <p class="section-sub-title">Controlla i tuoi sondaggi</p>
            </div>
            <!-- End of Section Title -->
            <div class="figures">
                <div class="figure">
                    <#list usersPoll as usersPoll>
                        <p>${usersPoll.title}</p>
                        <p><b>${usersPoll.ID}</b></p>
                    </#list>
                </div>
            </div>
        </div>
    </div>
</section>