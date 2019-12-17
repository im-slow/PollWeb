<div class="form-check ml-3 d-flex flex-column justify-content-center align-items-start">
    <#list question.QAnswer as q>
        <#if question.mandatory>
                <div class="col-md-6 form-check mb-3">
                    <input class="form-check-label pl-3" type="radio" id="radio${q?index}" name="radio${question.position}" value="${q}" required>
                    <label class="form-check-label pl-3" for="radio${q?index}">
                        ${q}
                    </label>
                </div>
        <#else>
            <div class="col-md-6 form-check mb-3">
                <input class="form-check-label pl-3" type="radio" id="radio${q?index}" name="radio${question.position}" value="option${q?index}">
                <label class="form-check-label pl-3" for="radio${q?index}">
                    ${q}
                </label>
            </div>
        </#if>
    </#list>
</div>