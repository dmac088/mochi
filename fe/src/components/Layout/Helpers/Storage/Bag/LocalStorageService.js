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

  function _removeItem(item) {
    const allItems = JSON.parse(localStorage.getItem("allItems")) || [];
    localStorage.setItem('allItems', allItems.filter(i => i.productCode !== item.productCode));
  }

  function _clearItems() {
    localStorage.setItem('allItems', []); 
  }

  function _getItems() {
    return JSON.parse(localStorage.getItem('allItems'));
  }

  function _setItems(items) {
    return localStorage.setItem('allItems', items);
  }

  function _getItem(productCode) {
    const allItems = JSON.parse(localStorage.getItem('allItems'));
    return allItems.filter(i => i.productCode === productCode);
  }

  return {
    setItems: _setItems,
    getItems: _getItems,
    getItem: _getItem,
    getService: _getService,
    removeItem: _removeItem,
    clearItems: _clearItems
  }
})();


export default LocalStorageService;