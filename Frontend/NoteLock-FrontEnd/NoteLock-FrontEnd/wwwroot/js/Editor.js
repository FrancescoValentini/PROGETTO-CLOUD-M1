var simplemde = new SimpleMDE();

function showEditorModal() {
    new bootstrap.Modal(document.getElementById('EditorModal')).show();
}

function getTitleText() {
    return document.getElementById('titleBar').value;
}

function getNoteText() {
    return simplemde.value();
}

function setTitleText(text) {
    document.getElementById('titleBar').value = text;
}

function setBodyText(text) {
    simplemde.value(text);
}
