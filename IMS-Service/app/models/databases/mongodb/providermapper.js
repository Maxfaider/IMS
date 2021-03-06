module.exports = function(objectmapper) {
    var collectionName = "providers";

    return {
        insert(documents, success, fail) {
            objectmapper.insert(collectionName, documents, success, fail);
        },
        update(params, updatedDocument, success, fail) {
            objectmapper.update(collectionName, params, {$set: updatedDocument}, success, fail);
        },
        find(params, success, fail) {
            objectmapper.find(collectionName, params, success, fail);
        },
        delete(params, success, fail) {
            objectmapper.delete(collectionName, params, success, fail);
        },
        count(params, success, fail) {
            objectmapper.count(collectionName, params, success, fail);
        }
    }
}
