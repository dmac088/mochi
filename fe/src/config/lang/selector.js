   const langSelector = {};

   langSelector['zh-HK'] = require('./zh-HK').default;
   langSelector['en-GB'] = require('./en-GB').default;

   export const getValue = (locale = 'en-GB') => {
     if(locale in langSelector) {
       return langSelector[locale];
     }
     return langSelector['en-GB'];
   }

   export default langSelector
