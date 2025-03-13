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

function encryptNote(){
    var text = getNoteText();
    var password = prompt("Insert password:");
    var encrypted = "";
    (async () => {
        encrypted = await encrypt(password, text);
    })();
    return encrypted;
}


function decryptNote(ciphertext){
    var password = prompt("Insert password:");
    var decrypted = "";
    (async () => {
        decrypted = await decrypt(password, ciphertext);
    })();
    setBodyText(decrypted);
    return decrypted;
}