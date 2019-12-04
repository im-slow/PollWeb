<div class="form-check ml-3">
    <#list question.QAnswer as q>
    <input class="form-check-input" type="checkbox" name="exampleRadios" id="exampleRadios1" value="option1">
    <label class="form-check-label pl-3" for="exampleRadios1">
        ${q}
    </label>
</#list>
</div>