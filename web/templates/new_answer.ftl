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
                                    <#include "answer-switch/singlechoise.ftl">
                                    <#break>
                                <#case "MULTIPLECHOISE">
                                    <#include "answer-switch/multiplechise.ftl">
                                    <#break>
                                <#case "SHORTTEXT">
                                    <#include "answer-switch/shorttext.ftl">
                                    <#break>
                                <#case "LONGTEXT">
                                    <#include "answer-switch/longtext.ftl">
                                    <#break>
                                <#case "DATE">
                                    <#include "answer-switch/date.ftl">
                                    <#break>
                                <#case "NUMBER">
                                    <#include "answer-switch/number.ftl">
                                    <#break>
                                <#default>
                                    ...
                            </#switch>
                            </div>
                        </div>
                        <div class="d-flex align-items-center align-text-center justify-content-between flex ml-4">
                            <h4><small>Note: ${question.note}</small></h4>
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
