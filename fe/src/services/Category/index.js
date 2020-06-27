

export  const findByCode = (categories, code) => {
    if(!categories) { return; }
    return categories.filter( o => o.data.categoryCode === code )[0];
}

export  const findByDesc = (categories, desc) => {
    if(!categories) { return; }
    return categories.filter( o => o.data.categoryDesc === desc )[0];
}

export const getChildren = (parent, categories, children) => {
    if(!categories) { return; }
    const c = categories.filter(o => o.data.parentCode === parent.data.categoryCode);
    if(!c) {return children;}
    c.map((child) => {
                      children.push(child);
                      getChildren(child, categories, children);
                      });

    return c;
  }