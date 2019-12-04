<#list question.QAnswer as q>
    <div class="row flex text-align radio form-check">
        <input class="form-check-input mt-2" type="radio" id="${q?index}" name="radio">
        <label class="form-check-label pl-3" for="radio">
            ${q}
        </label>
    </div>
</#list>