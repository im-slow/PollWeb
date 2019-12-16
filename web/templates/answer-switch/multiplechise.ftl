 <#list question.QAnswer as q>
     <div class="form-check checkbox ml-3">
        <#if question.mandatory>
            <input class="checkbox form-check-input" type="checkbox" name="check" id="${q?index}" value="option${q?index}" required>
        <#else>
            <input class="checkbox form-check-input" type="checkbox" name="check" id="${q?index}" value="option${q?index}">
        </#if>
        <label class="checkbox form-check-label pl-3" for="check">
            ${q}
        </label>
     </div>
</#list>