<section id="gtco-contact-form" class="bg-white pt-5" xmlns="http://www.w3.org/1999/html">
    <div class="container">
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <h2 class="section-title">${poll.title}</h2>
                <p class="section-sub-title">${poll.opentext}</p>
            </div>
        </div>
        <form class="border col-md-8 offset-md-2">
            <#list question as question>
                <div class="price-box">
                    <div class="card-body">
                        <div class="row align-items-center align-text-center justify-content-between flex-nowrap px-5">
                            <h6 id="number-js" class="text-orange font-weight-bold mr-2">${question.positionNumber}</h6>
                            <h6 class="question-name-js">${question.questionText}</h6>
                            <a href="javascript:void(0)" class="text-orange font-weight-bold a-click ml-2"><h3> + </h3></a>
                        </div>
                    </div>
                </div>
                <div class="price-box">
                    <div class="card-body">
                        <div class=" align-items-center align-text-center justify-content-between flex-nowrap px-5 pb-2">
                            <div class="col-md-12">
                            <#switch question.questionType>
                                <#case "SINGLECHOISE">
                                    <#list question.QAnswer as q>
                                    <div class="radio">
                                            <input type="radio" id="radio${q?index}" name="radio${q?index}" value="option${q?index}">
                                            <label class="pl-3" for="radio${q?index}">
                                                ${q}
                                            </label>
                                    </div>
                                    </#list>
                                    <#break>
                                <#case "MULTIPLECHOISE">
                                    <div class="form-check ml-3">
                                        <#list question.QAnswer as q>
                                            <input class="form-check-input" type="checkbox" name="exampleRadios" id="exampleRadios1" value="option1">
                                            <label class="form-check-label pl-3" for="exampleRadios1">
                                                ${q}
                                            </label>
                                        </#list>
                                    </div>
                                    <#break>
                                <#case "SHORTTEXT">
                                    ...
                                    <#break>
                                <#case "LONGTEXT">
                                    ...
                                    <#break>
                                <#case "DATE">
                                    ...
                                    <#break>
                                <#case "NUMBER">
                                    ...
                                    <#break>
                                <#default>
                                    ...
                            </#switch>
                            </div>
                        </div>
                        <div class="d-flex align-items-center align-text-center justify-content-between flex ml-4">
                            <h4><small>Note: ${question.questionText}</small></h4>
                        </div>
                    </div>
                </div>
            </#list>
        </form>
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <p class="section-sub-title">${poll.closetext}</p>
            </div>
        </div>
    </div>
</section>
