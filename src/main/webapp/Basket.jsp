<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
  <head>
    <title>��ٱ��� ������ ����</title>
    <style>
      /* ��ٱ��� ������ ��Ÿ�� */
      .container {
        margin: 20px;
        border: 1px solid gray;
        padding: 20px;
      }
      .item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
      }
      .item img {
        width: 50px;
        height: 50px;
        margin-right: 10px;
      }
      .item button {
        margin-left: 10px;
      }
      .total {
        margin-top: 20px;
        font-weight: bold;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>��ٱ���</h1>
      <div class="cart"></div>
      <div class="total">�� ���� �ݾ�: 0��</div>
      <button onclick="checkout()">�����ϱ�</button>
    </div>
    <script>
      // ��ǰ ������
      const products = [
        {
          id: 1,
          name: '��ǰ 1',
          price: 1000,
          image: 'https://via.placeholder.com/50x50',
        },
        {
          id: 2,
          name: '��ǰ 2',
          price: 2000,
          image: 'https://via.placeholder.com/50x50',
        },
        {
          id: 3,
          name: '��ǰ 3',
          price: 3000,
          image: 'https://via.placeholder.com/50x50',
        },
      ];

      // ��ٱ��� ��ǰ ���
      let cartItems = [];

      // ��ǰ�� ��ٱ��Ͽ� �߰��ϴ� �Լ�
      function addToCart(productId) {
        const product = products.find((p) => p.id === productId);
        const cartItem = cartItems.find((c) => c.id === productId);
        if (cartItem) {
          cartItem.quantity += 1;
        } else {
          cartItems.push({ ...product, quantity: 1 });
        }
        renderCart();
      }

      // ��ǰ�� ������ �����ϴ� �Լ�
      function changeQuantity(productId, quantity) {
        const cartItem = cartItems.find((c) => c.id === productId);
        cartItem.quantity = quantity;
        renderCart();
      }

      // ��ǰ�� ��ٱ��Ͽ��� �����ϴ� �Լ�
      function removeFromCart(productId) {
        cartItems = cartItems.filter((c) => c.id !== productId);
        renderCart();
      }

      // ��ٱ��� �������� �������ϴ� �Լ�
      function renderCart() {
        const cart = document.querySelector('.cart');
        cart.innerHTML = '';
        cartItems.forEach((item) => {
          const div = document.createElement('div');
          div.className = 'item';
          div.innerHTML = `
              <img src="${item.image}" alt="${item.name}">
              <span>${item.name} - ${item.price}��</span>
              <input type="number" value="${item.quantity}" min="1" max="10" onchange="changeQuantity(${item.id}, this.value)">
              <button onclick="removeFromCart(${item.id})">����</button>
            `;
            cart.appendChild(div);
          });
          const total = document.querySelector('.total');
          const totalPrice = cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0);
          total.textContent = `�� ���� �ݾ�: ${totalPrice}��`;
        }

        // �����ϱ� ��ư�� Ŭ������ �� ȣ��Ǵ� �Լ�
        function checkout() {
          alert(`���� �ݾ�: ${cartItems.reduce((acc, item) => acc + item.price * item.quantity, 0)}��`);
          cartItems = [];
          renderCart();
        }

        // �ʱ� ������ �ε� �� ��ǰ ����� ������
        renderCart();
      </script>
    </body>
  </html>
          
         
