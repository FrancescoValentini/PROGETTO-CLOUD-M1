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

async function encryptNote() {
    var text = getNoteText();

    var password = prompt("Insert password:");
    if (!password) {
        return null;
    }

    try {
        let encrypted = await encrypt(password, text);
        return encrypted;
    } catch (error) {
        console.error("Errore durante la crittografia:", error);    
        return null;
    }
}



async function decryptNote(ciphertext) {
    var password = prompt("Insert password:");
    if (!password) {
        return null;
    }

    try {
        let decrypted = await decrypt(password, ciphertext);
        setBodyText(decrypted);
        return decrypted;
    } catch (error) {
        console.error("Errore durante la decrittazione:", error);
        return null;
    }
}
