<#list question.QAnswer as q>
    <div class="radio">
        <input type="radio" id="radio${q?index}" name="radio${q?index}" value="option${q?index}">
        <label class="pl-3" for="radio${q?index}">
            ${q}
        </label>
    </div>
</#list>