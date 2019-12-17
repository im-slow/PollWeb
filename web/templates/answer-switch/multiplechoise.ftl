<div class="form-check ml-3 d-flex flex-column justify-content-center align-items-start">
    <#list question.QAnswer as q>
        <div class="col-md-12 form-check mb-3">
            <input class="form-check-label pl-3 ml-4" type="checkbox" name="exampleRadios" id="exampleRadios1" value="${q}">
            <label class="form-check-label pl-3 ml-4" for="exampleRadios1">
                ${q}
            </label>
        </div>
    </#list>
</div>