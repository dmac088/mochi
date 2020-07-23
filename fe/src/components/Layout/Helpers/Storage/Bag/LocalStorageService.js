// LocalStorageService.js
const LocalStorageService = (function () {
  var _service;

  function _getService() {
    if (!_service) {
      _service = this;
      return _service
    }
    return _service
  }

  function _addItem(item) {
    const allItems = JSON.parse(localStorage.getItem("allItems")) || [];
    allItems.push(item);
    localStorage.setItem('allItems', JSON.stringify(allItems));
  }

  function _removeItem(item) {
    const allItems = JSON.parse(localStorage.getItem("allItems")) || [];
    localStorage.setItem('allItems', allItems.filter(i => i.productCode !== item.productCode));
  }

  function _clearItems() {
    localStorage.setItem('allItems', []);
  }

  function _getItems() {
    return localStorage.getItem('allItems');
  }

  function _getItem(productCode) {
    return localStorage.getItem('allItems').filter(i => i.productCode === productCode);
  }

  return {
    getService: _getService,
    addItem: _addItem,
    removeItem: _removeItem,
    getItems: _getItems,
    getItem: _getItem,
    clearItems: _clearItems
  }
})();


export default LocalStorageService;