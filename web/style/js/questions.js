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
    basicAnswer = $('.question-option-js').clone();
    answerType = $('#answer-type-js').clone();
    allField = $('#all-field-js').clone();
    minMaxField = $('#min-max-field-js').clone();
    answerField = $('#answer-field-js').clone();
    listChild();
});

//Add new Question
$(document).on('click', '#another-question-js', () => {
    var test = $('#card-header-js-plh').clone(true);
    var cloneBasic = test.clone(true).attr('id', 'card-header-js');
    $('.accordion').append(cloneBasic).hide().fadeIn(600);
    listChild();
});

//Remove Questions
$(document).on('click', '.remove-question-js', function (e) {
    e.stopPropagation();
    const elementremove = $(this);
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
                const pollID = $('#pollID-js').attr('value');
                if ($(this).closest('#card-header-js').find('.proxy-id-js').length) {
                    $.ajax({
                        type: 'POST',
                        data: { id: idvalue, pollID },
                        url: `http://localhost:8080/removeQuestion`,
                        success: function() {
                            console.log(elementremove);
                            elementremove.closest('#card-header-js').remove();
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
        curnode.find(`#validationCustom01`).attr('name', `questname${counter}`);
        curnode.find('.min-js').attr('name', `min${counter}`);
        curnode.find('.max-js').attr('name', `max${counter}`);
        curnode.find('.info-js').attr('name', `info${counter}`);
        curnode.find('.answer-js').attr('name', `domanda${counter}`);
        curnode.find('.questnumber-js').attr('name', `numberquest${counter}`);
        curnode.find('.proxy-id-js').attr('name', `id${counter}`);
        curnode.find('.label-tipology-js').attr('for', `exampleFormControlSelect${counter}`);
        curnode.find('select').attr('id', `exampleFormControlSelect${counter}`);
        curnode.removeAttr('hidden');
        curnode.find('#append-answer-js').find('div').each(function() {
            $(this).find('input').attr('name', `domanda${counter}`);
        });
        curnode.find('.questnumber-js').val(counter);
        curnode.find('#number-js').html(counter);
        curnode.find('.question-type-js').attr('name', `questionType${counter}`);
    });
    $('#questnumbers').val(counter); // Number of all quest
}

//add new Single choise or multiple choise answer
$(document).on('click', '#new-answer-js', function() {
    const parent = $(this).parent().parent().parent();
    basicAnswerclone = parent.find('.question-option-js').first().clone();
    basicAnswerclone.find('.answer-js').val( '');
    parent.find('#append-answer-js').append(basicAnswerclone);
});

//delete question
$(document).on('click', '.delete_option_quest', function () {
    let element = 0;
    $(this).closest('#append-answer-js').children('div').each(function () {
        element++;
    });
    if (element>1) {
        $(this).closest('.question-option-js').remove(); //parent con la vecchia "X"
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

//Menage the different types of questions
$(document).on('change', '.form-control', function() {
    var val = $(this).val();
    var name = $(this).find(':selected').attr('name');
    console.log(name, val);
    var numberquest = $(this).closest('#card-header-js').find('#number-js').text();
    const parent = $(this).closest('.section-content');
    switch(val) {
        case "answer":
            choiseClone = parent.find("#answer-field-js-plh").clone();
            choiseClone.find(".answer-js").attr('name', `domanda${numberquest}`);
            break;
        case "min-max":
            choiseClone = parent.find("#min-max-field-js-plh").clone();
            choiseClone.find(".max-js").attr("name", `max${numberquest}`);
            choiseClone.find(".min-js").attr('name', `min${numberquest}`);
            choiseClone.find(".answer-js").attr('name', `domanda${numberquest}`);
            break;
        case "all":
            choiseClone = parent.find("#all-field-js-plh").clone();
            choiseClone.find(".answer-js").attr('name', `domanda${numberquest}`);
            choiseClone.find(".max-js").attr("name", `max${numberquest}`);
            choiseClone.find(".min-js").attr('name', `min${numberquest}`);
            break;
        case "date":
            choiseClone = parent.find('#date-field-js-plh').clone();
            choiseClone.find(".answer-js").attr('name', `domanda${numberquest}`);
            choiseClone.find(".max-js").attr("name", `max${numberquest}`);
            choiseClone.find(".min-js").attr('name', `min${numberquest}`);
    }
    choiseClone.removeAttr('hidden');
    $(this).closest('#card-header-js').find('#answer-type-js').html(choiseClone);
    $(this).closest('#card-header-js').find('.question-type-js').attr('name', `questionType${numberquest}`);
    $(this).closest('#card-header-js').find('.question-type-js').attr('value', name);
});

$(document).on('click', '.changestatuspoll-js', function () {
    var idpoll = $('#pollID-js').attr('value');
    swal({
        title: 'Rendere il sondaggio pubblico?',
        text: 'Non potrai più modificare le domande inserite',
        icon: 'warning',
        buttons: ['Annulla', 'Conferma']
    }).then((willDelete) => {
        if (willDelete) {
            window.location = `http://localhost:8080/pubblicasondaggio?id=${idpoll}`;
        } else {
            console.log('Dismissed');
        }
    });
});

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function newUser() {
    swal({
        text: 'Inserisci un email per aggiungere un utente al sondaggio (es. name@email.com):',
        content: {
            element: "input",
            attributes: {
                placeholder: "Inserisci email",
                type: "text"
            },
        },
        button: {
            text: "Aggiungi",
            closeModal: false,
        },
    }).then(name => {
        if (!name || !validateEmail(name)) {
            return swal("L'email non è stata scritta nel modo corretto!");
        }
        if (name) {
            const nome = name;
            const password = Math.random().toString(36).slice(2);
            const role = 'USER';
            var url_string = window.location.href;
            var captured = /id=([^&]+)/.exec(url_string)[1]; // Value is in [1]
            const url = captured ? captured : 'null';
            var data = { nome: nome, password: password, role: role, IDpoll: url };
            var saveData = $.ajax({
                type: 'POST',
                url: "http://localhost:8080/aggiungiutente", //http://localhost:8080/rispondisondaggio?id="+url,
                data: data,
                dataType: "text",
                success: function(resultData) {
                    swal({
                        title: "Utente creato con successo",
                        text: "É stata inviata un email all'indirizzo: \""+nome+"\", con la password e l'indirizzo URL per rispondere al sondaggio. "+"Debug, Password: "+password+", IDpoll:"+url+".",
                    });
                }
            });
        }
    }).catch(err => {
        if (err) {
            swal("ATTENZIONE!", "La richiesta AJAX non è andata a buon fine!", "error");
        } else {
            swal.stopLoading();
            swal.close();
        }
    });
}
