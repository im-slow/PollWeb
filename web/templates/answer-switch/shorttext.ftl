<div class="form-check ml-3 d-flex flex-column justify-content-center align-items-start">
        <div class="form-input col-md-12 mb-3">
            <#if question.mandatory>
                <input class="form-control" type="text" name="answer${question.position}" placeholder="Scrivi qui la tua risposta" required>
            <#else>
                <input class="form-control" type="text" name="answer${question.position}" placeholder="Scrivi qui la tua risposta">
            </#if>
        </div>
</div>