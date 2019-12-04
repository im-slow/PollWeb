 <#list question.QAnswer as q>
     <div class="form-check checkbox ml-3">
        <input class="checkbox form-check-input" type="checkbox" name="exampleRadios" id="exampleRadios1" value="option1">
        <label class="checkbox form-check-label pl-3" for="exampleRadios1">
            ${q}
        </label>
     </div>
</#list>