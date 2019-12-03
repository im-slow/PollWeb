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
    basicQuestion = $('#card-header-js').clone();
    basicAnswer = $('#question-option-js').clone();
});

//Add new Question
$(document).on('click', '#another-question-js', () => {
    $('.accordion').append(basicQuestion.clone()).hide().fadeIn(600);
    listChild();
});

//Remove Questions
$(document).on('click', '.remove-question-js', function (e) {
    e.stopPropagation();
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
                $(this).parent().parent().parent().remove();
                listChild();
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

//List and enable Boostrap Collapse Accordion
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
        curnode.find('#number-js').html(counter);
    });
}

//add new Single choise or multiple choise answer
$(document).on('click', '#answer-js', () => {
    console.log($(this).parent().parent());
    $(this).parent().parent().parent().find('#append-answer-js').append(basicAnswer.clone()).hide().fadeIn(600);
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