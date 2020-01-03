<div class="d-flex flex-row justify-content-around align-items-center align-text-center">
    <p><b class="mr-2"># minimo: </b>${question.minimum}</p>
    <p><b class="mr-2"># massimo: </b>${question.maximum}</p>
</div>
<div class="form-check ml-3 d-flex flex-column justify-content-center align-items-start">
    <#list question.QAnswer as q>
        <div class="col-md-12 form-check mb-3">
            <input id="exampleRadios1" class="form-check-label pl-3" type="checkbox" name="answer${question.position}" value="${q}">
            <label for="exampleRadios1" class="form-check-label pl-3">
                ${q}
            </label>
        </div>
    </#list>
</div>