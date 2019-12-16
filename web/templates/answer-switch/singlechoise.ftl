<#list question.QAnswer as q>
    <div class="radio d-flex flex-column justify-content-center align-items-start">
        <input type="radio" id="radio${q?index}" name="radio${q?index}" value="option${q?index}">
        <label class="pl-3" for="radio${q?index}">
    <div class="row flex text-align radio form-check">
        <#if question.mandatory>
            <input class="form-check-input mt-2" type="radio" id="${q?index}" name="radio" value="${q?index}" required>
        <#else>
            <input class="form-check-input mt-2" type="radio" id="${q?index}" name="radio" value="${q?index}">
        </#if>
        <label class="form-check-label pl-3" for="radio">
            ${q}
        </label>
    </div>
</#list>