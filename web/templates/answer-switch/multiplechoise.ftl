<div class="form-check ml-3 d-flex flex-column justify-content-center align-items-start">
    <#list question.QAnswer as q>
        <div class="col-md-12 form-check mb-3">
            <label for="exampleRadios1" class="form-check-label pl-3">
                ${q}
            </label>
            <input id="exampleRadios1" class="form-check-label pl-3" type="checkbox" name="answer${question.position}" value="${q}">
        </div>
    </#list>
</div>