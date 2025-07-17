// Global variables
let products = [];
let purchases = [];
let purchaseModal;
let purchaseDetailsModal;

// DOM Elements
const productsSection = document.getElementById('products-section');
const purchasesSection = document.getElementById('purchases-section');
const productsLink = document.getElementById('products-link');
const purchasesLink = document.getElementById('purchases-link');
const refreshProductsBtn = document.getElementById('refresh-products');
const refreshPurchasesBtn = document.getElementById('refresh-purchases');
const newPurchaseBtn = document.getElementById('new-purchase-btn');
const productsTableBody = document.getElementById('products-table-body');
const purchasesTableBody = document.getElementById('purchases-table-body');
const addItemBtn = document.getElementById('add-item');
const savePurchaseBtn = document.getElementById('save-purchase');
const purchaseItemsContainer = document.getElementById('purchase-items');

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    // Initialize Bootstrap modals
    purchaseModal = new bootstrap.Modal(document.getElementById('purchaseModal'));
    purchaseDetailsModal = new bootstrap.Modal(document.getElementById('purchaseDetailsModal'));
    
    // Set up event listeners
    setupEventListeners();
    
    // Load initial data
    loadProducts();
});

// Set up event listeners
function setupEventListeners() {
    // Navigation
    productsLink.addEventListener('click', showProductsSection);
    purchasesLink.addEventListener('click', showPurchasesSection);
    
    // Refresh buttons
    refreshProductsBtn.addEventListener('click', loadProducts);
    refreshPurchasesBtn.addEventListener('click', loadPurchases);
    
    // New purchase
    newPurchaseBtn.addEventListener('click', openNewPurchaseModal);
    
    // Add item to purchase
    addItemBtn.addEventListener('click', addPurchaseItem);
    
    // Save purchase
    savePurchaseBtn.addEventListener('click', savePurchase);
}

// Show products section
function showProductsSection() {
    productsSection.style.display = 'block';
    purchasesSection.style.display = 'none';
    productsLink.classList.add('active');
    purchasesLink.classList.remove('active');
}

// Show purchases section
function showPurchasesSection() {
    productsSection.style.display = 'none';
    purchasesSection.style.display = 'block';
    productsLink.classList.remove('active');
    purchasesLink.classList.add('active');
    loadPurchases();
}

// Load products from API
function loadProducts() {
    fetch('/products')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            products = data;
            renderProducts();
        })
        .catch(error => {
            console.error('Error loading products:', error);
            alert('Error loading products. Please try again.');
        });
}

// Render products in the table
function renderProducts() {
    productsTableBody.innerHTML = '';
    
    if (products.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="7" class="text-center">No products found</td>';
        productsTableBody.appendChild(row);
        return;
    }
    
    products.forEach(product => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${product.productId}</td>
            <td>${product.name}</td>
            <td>${product.category ? product.category.category : 'N/A'}</td>
            <td>${product.barcode}</td>
            <td>$${product.price}</td>
            <td>${product.stock}</td>
            <td>${product.active ? 
                '<span class="badge bg-success">Active</span>' : 
                '<span class="badge bg-danger">Inactive</span>'}</td>
        `;
        productsTableBody.appendChild(row);
    });
    
    // Also update product selects in the purchase form
    updateProductSelects();
}

// Load purchases from API
function loadPurchases() {
    fetch('/purchases')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            purchases = data;
            renderPurchases();
        })
        .catch(error => {
            console.error('Error loading purchases:', error);
            alert('Error loading purchases. Please try again.');
        });
}

// Render purchases in the table
function renderPurchases() {
    purchasesTableBody.innerHTML = '';
    
    if (purchases.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="6" class="text-center">No purchases found</td>';
        purchasesTableBody.appendChild(row);
        return;
    }
    
    purchases.forEach(purchase => {
        const row = document.createElement('tr');
        const date = new Date(purchase.fecha).toLocaleString();
        
        row.innerHTML = `
            <td>${purchase.idCompra}</td>
            <td>${purchase.idCliente}</td>
            <td>${date}</td>
            <td>${purchase.medioPago}</td>
            <td>${getStatusBadge(purchase.estado)}</td>
            <td>
                <button class="btn btn-sm btn-info view-purchase" data-id="${purchase.idCompra}">
                    Ver detalles
                </button>
            </td>
        `;
        purchasesTableBody.appendChild(row);
    });
    
    // Add event listeners to view buttons
    document.querySelectorAll('.view-purchase').forEach(button => {
        button.addEventListener('click', function() {
            const purchaseId = this.getAttribute('data-id');
            openPurchaseDetailsModal(purchaseId);
        });
    });
}

// Get status badge HTML
function getStatusBadge(status) {
    if (status === 'P') {
        return '<span class="badge bg-success">Pagado</span>';
    } else if (status === 'C') {
        return '<span class="badge bg-danger">Cancelado</span>';
    } else {
        return '<span class="badge bg-warning">Pendiente</span>';
    }
}

// Open new purchase modal
function openNewPurchaseModal() {
    // Reset form
    document.getElementById('purchase-form').reset();
    
    // Clear purchase items except the first one
    const items = purchaseItemsContainer.querySelectorAll('.purchase-item');
    for (let i = 1; i < items.length; i++) {
        items[i].remove();
    }
    
    // Reset the first item
    const firstItem = purchaseItemsContainer.querySelector('.purchase-item');
    if (firstItem) {
        const select = firstItem.querySelector('.product-select');
        const quantity = firstItem.querySelector('.quantity');
        const total = firstItem.querySelector('.total');
        
        select.value = '';
        quantity.value = '';
        total.value = '';
    }
    
    // Update product selects
    updateProductSelects();
    
    // Show modal
    purchaseModal.show();
}

// Add purchase item to form
function addPurchaseItem() {
    const itemTemplate = purchaseItemsContainer.querySelector('.purchase-item').cloneNode(true);
    
    // Reset values
    itemTemplate.querySelector('.product-select').value = '';
    itemTemplate.querySelector('.quantity').value = '';
    itemTemplate.querySelector('.total').value = '';
    
    // Add event listeners
    setupItemEventListeners(itemTemplate);
    
    // Add to container
    purchaseItemsContainer.appendChild(itemTemplate);
}

// Setup event listeners for purchase item
function setupItemEventListeners(itemElement) {
    const productSelect = itemElement.querySelector('.product-select');
    const quantityInput = itemElement.querySelector('.quantity');
    const totalInput = itemElement.querySelector('.total');
    const removeButton = itemElement.querySelector('.remove-item');
    
    // Product selection changes
    productSelect.addEventListener('change', function() {
        updateItemTotal(productSelect, quantityInput, totalInput);
    });
    
    // Quantity changes
    quantityInput.addEventListener('input', function() {
        updateItemTotal(productSelect, quantityInput, totalInput);
    });
    
    // Remove item
    removeButton.addEventListener('click', function() {
        if (purchaseItemsContainer.querySelectorAll('.purchase-item').length > 1) {
            itemElement.remove();
        } else {
            alert('You need at least one product in the purchase.');
        }
    });
}

// Update item total based on product and quantity
function updateItemTotal(productSelect, quantityInput, totalInput) {
    const productId = productSelect.value;
    const quantity = parseInt(quantityInput.value) || 0;
    
    if (productId && quantity > 0) {
        const product = products.find(p => p.productId == productId);
        if (product) {
            const total = product.price * quantity;
            totalInput.value = total.toFixed(2);
        }
    } else {
        totalInput.value = '';
    }
}

// Update all product selects with current products
function updateProductSelects() {
    const productSelects = document.querySelectorAll('.product-select');
    
    productSelects.forEach(select => {
        // Save current value
        const currentValue = select.value;
        
        // Clear options except the first one
        while (select.options.length > 1) {
            select.remove(1);
        }
        
        // Add product options
        products.forEach(product => {
            if (product.active && product.stock > 0) {
                const option = document.createElement('option');
                option.value = product.productId;
                option.textContent = `${product.name} - $${product.price} (Stock: ${product.stock})`;
                select.appendChild(option);
            }
        });
        
        // Restore value if possible
        if (currentValue) {
            select.value = currentValue;
        }
    });
    
    // Setup event listeners for all items
    document.querySelectorAll('.purchase-item').forEach(setupItemEventListeners);
}

// Save purchase
function savePurchase() {
    // Validate form
    const clientId = document.getElementById('clientId').value;
    const paymentMethod = document.getElementById('paymentMethod').value;
    const comment = document.getElementById('comment').value;
    
    if (!clientId || !paymentMethod) {
        alert('Please fill in all required fields.');
        return;
    }
    
    // Get items
    const items = [];
    const itemElements = purchaseItemsContainer.querySelectorAll('.purchase-item');
    
    for (const itemElement of itemElements) {
        const productId = itemElement.querySelector('.product-select').value;
        const quantity = parseInt(itemElement.querySelector('.quantity').value);
        const total = parseFloat(itemElement.querySelector('.total').value);
        
        if (!productId || !quantity) {
            alert('Please fill in all product details.');
            return;
        }
        
        items.push({
            idProducto: parseInt(productId),
            cantidad: quantity,
            total: total,
            active: true
        });
    }
    
    // Create purchase object
    const purchase = {
        idCliente: clientId,
        fecha: new Date().toISOString(),
        medioPago: paymentMethod,
        comentario: comment,
        estado: 'P', // Pagado
        items: items
    };
    
    // Send to API
    fetch('/purchases/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(purchase)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        alert('Purchase saved successfully!');
        purchaseModal.hide();
        loadPurchases();
        loadProducts(); // Reload products to update stock
    })
    .catch(error => {
        console.error('Error saving purchase:', error);
        alert('Error saving purchase. Please try again.');
    });
}

// Open purchase details modal
function openPurchaseDetailsModal(purchaseId) {
    const purchase = purchases.find(p => p.idCompra == purchaseId);
    
    if (!purchase) {
        alert('Purchase not found.');
        return;
    }
    
    // Fill in details
    document.getElementById('detail-id').textContent = purchase.idCompra;
    document.getElementById('detail-client').textContent = purchase.idCliente;
    document.getElementById('detail-date').textContent = new Date(purchase.fecha).toLocaleString();
    document.getElementById('detail-payment').textContent = purchase.medioPago;
    document.getElementById('detail-status').textContent = getStatusText(purchase.estado);
    document.getElementById('detail-comment').textContent = purchase.comentario || 'N/A';
    
    // Fill in items
    const itemsContainer = document.getElementById('detail-items');
    itemsContainer.innerHTML = '';
    
    if (!purchase.items || purchase.items.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = '<td colspan="4" class="text-center">No items found</td>';
        itemsContainer.appendChild(row);
    } else {
        purchase.items.forEach(item => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${item.idProducto}</td>
                <td>${item.cantidad}</td>
                <td>$${item.total.toFixed(2)}</td>
                <td>${item.active ? 
                    '<span class="badge bg-success">Active</span>' : 
                    '<span class="badge bg-danger">Inactive</span>'}</td>
            `;
            itemsContainer.appendChild(row);
        });
    }
    
    // Show modal
    purchaseDetailsModal.show();
}

// Get status text
function getStatusText(status) {
    if (status === 'P') {
        return 'Pagado';
    } else if (status === 'C') {
        return 'Cancelado';
    } else {
        return 'Pendiente';
    }
}