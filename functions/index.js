/**
deploy   ---->     firebase deploy --only functions


 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

const { onRequest, onCall } = require("firebase-functions/v2/https");
const { onDocumentCreated } = require("firebase-functions/v2/firestore");
const { logger } = require("firebase-functions");


exports.alisverisYuklendi = onDocumentCreated("alisveris/sepet", (event) => {
  const snapshot = event.data;
  const yeniVeri = snapshot.data();
  const fiyat = yeniVeri.fiyat;
  const miktar = yeniVeri.miktar;

  const ciro = fiyat * miktar;

  return snapshot.ref.update({ ciro: ciro });
});

