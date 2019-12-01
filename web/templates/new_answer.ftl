<section id="gtco-contact-form" class="bg-white pt-5" xmlns="http://www.w3.org/1999/html">
    <div class="container">
        <div class="section-content">
            <!-- Section Title -->
            <div class="title-wrap">
                <h2 class="section-title">${poll.title}</h2>
                <p class="section-sub-title">${poll.opentext}</p>
                <#list question as question>
                <div class="col-md-12">
                    <label>${question.questionText}</label>
                    <#if question.questionType == "SINGLECHOISE">
                        <div class="form-check ml-3">
                            <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1">
                            <label class="form-check-label pl-3" for="exampleRadios1">
                                Risposta 1
                            </label>
                        </div>
                    <#else>
                        <div class="form-check ml-3">
                            <input class="form-check-input" type="checkbox" name="exampleRadios" id="exampleRadios1" value="option1">
                            <label class="form-check-label pl-3" for="exampleRadios1">
                                Risposta 1
                            </label>
                        </div>
                    </#if>
                </div>
                </#list>
                <p>${poll.closetext}</p>
            </div>
            <!-- End of Section Title -->

        </div>
    </div>
</section>