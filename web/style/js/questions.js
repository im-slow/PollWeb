var basicAnswer;
var basicQuestion;


var el = document.getElementById('accordionExample');

var sortable = Sortable.create(el, {
    onEnd: function (evt) {
        listChild()
    },
    animation: 150,
});

//Question
$(document).ready(function () {
    basicQuestion = $('#card-header-js-plh').clone();
    basicAnswer = $('#question-option-js').clone();
    listChild();
});

//Add new Question
$(document).on('click', '#another-question-js', () => {
    $('.accordion').append(basicQuestion.clone()).hide().fadeIn(600);
    listChild();
});

//Remove Questions
$(document).on('click', '.remove-question-js', function (e) {
    e.stopPropagation();
    const elementremv = $(this);
    let element = 0;
    $('.accordion > div').each(function () {
        element++;
    });
    if (element > 1) {
        swal({
            title: 'Sei sicuro di voler rimuovere la domanda?',
            text: 'Non sarai in grado di recuperarla successivamente!',
            icon: 'warning',
            buttons: ['Annulla', 'Elimina']
        }).then((willDelete) => {
            if (willDelete) {
                const idvalue = $(this).closest('#card-header-js').find('.proxy-id-js').attr('value');
                if ($(this).closest('#card-header-js').find('.proxy-id-js').length) {
                    $.ajax({
                        type: 'POST',
                        data: { id: idvalue },
                        url: `http://localhost:8080/removeQuestion`,
                        success: function() {
                            elementremv.closest('#card-header-js').remove();
                            listChild();
                        },
                        error: function(data) {
                            console.log(`error${data}`);
                        }
                    });
                } else {
                    $(this).parent().parent().parent().remove();
                    listChild();
                }
            } else {
                console.log('Dismissed');
            }
        });
    }
});

//Keep the answer name on focus
$(document).on('change', '.question-input-js', function () {
    const node = $(this).parent().parent().parent().parent().find('.question-name-js');
    if ($(this).val() === '') {
        node.html('Nuova Domanda');
    } else {
        node.html($(this).val());
    }
});

//List and enable Boostrap Collapse Accordion and set input name
function listChild() {
    let counter = 0;
    $('.accordion > div').each(function () {
        const curnode = $(this);
        curnode.find('.card-header').attr('id', `heading${++counter}`);
        curnode.find('.collpase-header-js').attr('data-target', `#collapse${counter}`);
        curnode.find('.collpase-header-js').attr('aria-controls', `collapse${counter}`);
        curnode.find('.card-body-js').attr('aria-labelledby', `heading${counter}`);
        curnode.find('.card-body-js').removeClass('show');
        curnode.find('.card-body-js').attr('id', `collapse${counter}`);
        curnode.find('#validationCustom01').attr('name', `questname${counter}`);
        curnode.find('#min-js').attr('name', `min${counter}`);
        curnode.find('#max-js').attr('name', `max${counter}`);
        curnode.find('#info-js').attr('name', `info${counter}`);
        curnode.find('#answer-js').attr('name', `domanda${counter}`);
        curnode.find('#questnumber').attr('name', `numberquest${counter}`);
        curnode.find('.proxy-id-js').attr('name', `id${counter}`);
        curnode.removeAttr('hidden');
        curnode.find('#append-answer-js').find('div').each(function() {
            $(this).find('input').attr('name', `domanda${counter}`);
        });
        curnode.find('#questnumber').val(counter);
        curnode.find('#number-js').html(counter);
    });
    $('#questnumbers').val(counter); // Number of all quest
}

//add new Single choise or multiple choise answer
$(document).on('click', '#new-answer-js', function() {
    const parent = $(this).parent().parent().parent();
    basicAnswerclone = parent.find('#question-option-js').clone();
    basicAnswerclone.find('#answer-js').val( '');
    parent.find('#append-answer-js').append(basicAnswerclone).hide().fadeIn(600);
});

//delete question
$(document).on('click', '.delete_option_quest', function () {
    let element = 0;
    $(this).closest('#append-answer-js').children('div').each(function () {
        element++;
    });
    if (element>1) {
        $(this).closest('#question-option-js').remove(); //parent con la vecchia "X"
    }
});

(function() {
    'use strict';
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();