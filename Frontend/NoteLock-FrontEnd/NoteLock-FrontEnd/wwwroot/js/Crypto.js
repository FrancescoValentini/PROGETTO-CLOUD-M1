// Genera un array di byte casuali
function getRandomBytes(length) {
    return crypto.getRandomValues(new Uint8Array(length));
}

// Deriva una chiave da una password e un salt
async function deriveKeyFromPassword(password, salt) {
    const encoder = new TextEncoder();
    const keyMaterial = await crypto.subtle.importKey(
        "raw",
        encoder.encode(password),
        { name: "PBKDF2" },
        false,
        ["deriveKey"]
    );
    return crypto.subtle.deriveKey(
        {
            name: "PBKDF2",
            salt: salt,
            iterations: 100000,
            hash: "SHA-256"
        },
        keyMaterial,
        { name: "AES-GCM", length: 256 },
        false,
        ["encrypt", "decrypt"]
    );
}

// Cifra un testo
async function encrypt(password, text) {
    const iv = getRandomBytes(12); // IV di 12 byte
    const salt = iv; // Usare lo stesso IV come salt
    const key = await deriveKeyFromPassword(password, salt);

    const encoder = new TextEncoder();
    const encodedText = encoder.encode(text);

    const ciphertext = await crypto.subtle.encrypt(
        {
            name: "AES-GCM",
            iv: iv
        },
        key,
        encodedText
    );

    const combined = new Uint8Array(iv.length + new Uint8Array(ciphertext).length);
    combined.set(iv, 0);
    combined.set(new Uint8Array(ciphertext), iv.length);

    return btoa(String.fromCharCode(...combined));
}

// Decifra un testo
async function decrypt(password, ciphertext) {
    const combined = Uint8Array.from(atob(ciphertext), c => c.charCodeAt(0));
    const iv = combined.slice(0, 12); // IV di 12 byte
    const salt = iv; // Usare lo stesso IV come salt
    const encryptedData = combined.slice(12);

    const key = await deriveKeyFromPassword(password, salt);

    const decrypted = await crypto.subtle.decrypt(
        {
            name: "AES-GCM",
            iv: iv
        },
        key,
        encryptedData
    );

    const decoder = new TextDecoder();
    return decoder.decode(decrypted);
}

// Estrae informazioni da un ciphertext
function ciphertextInfo(ciphertext) {
    const combined = Uint8Array.from(atob(ciphertext), c => c.charCodeAt(0));
    const iv = combined.slice(0, 12);
    const encryptedData = combined.slice(12);

    return {
        iv: btoa(String.fromCharCode(...iv)),
        ciphertext: btoa(String.fromCharCode(...encryptedData))
    };
}

