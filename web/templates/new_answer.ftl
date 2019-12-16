<section id="gtco-contact-form" class="bg-white pt-5" xmlns="http://www.w3.org/1999/html">
    <div class="section-content">
        <!-- Section Title -->
        <#--        <div class="title-wrap">-->
        <#--            <h2 class="section-title">${poll.title}</h2>-->
        <#--            <p class="section-sub-title">${poll.opentext}</p>-->
        <#--        </div>-->
        <div class="col justify-content-center align-items-center title-wrap mb-3">
            <h2 class="section-title">${poll.title}</h2>
            <p class="section-sub-title">${poll.opentext}</p>
        </div>
        <form class="" action="/" name="testanswer" method="post">
            <div class="price-box card col-md-8 offset-md-2 m-t-4">
                <div class="border-bottom border-top border-left border-right">
                    <#list question as question>
                        <div class="price-box">
                            <div class="card-body align-items-center justify-content-center">
                                <div class="float-left align-items-center justify-content-center ml-3">
                                    <h6 id="number-js" class="text-orange font-weight-bold mr-2">${question.position}</h6>
                                </div>
                                <div class="d-flex flex-column align-items-center align-text-center justify-content-center px-5">
                                    <h6 class="question-name-js">${question.questionText}</h6>
                                    <#if question.note != "">
                                        <p>
                                            <b class="mr-2">Note: </b>
                                            ${question.note}
                                        </p>
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="price-box">
                            <div class="card-body">
                                <div class=" align-items-center justify-content-between flex-nowrap px-5 pb-2">
                                    <div class="col-md-12">
                                        <#switch question.questionType>
                                            <#case "SINGLECHOISE">
                                                <#include "answer-switch/singlechoise.ftl">
                                                <#break>
                                            <#case "MULTIPLECHOISE">
                                                <#include "answer-switch/multiplechoise.ftl">
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
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="d-flex flex-column justify-content-center align-items-stretch py-5 offset-md-2 m-t-4 col-md-8">
                <input type="submit" value="Rispondi" class="btn btn-secondary rounded-pill btn-red a-click" />
            </div>
        </form>
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <p class="section-sub-title">${poll.closetext}</p>
            </div>
        </div>
    </div>
</section>
