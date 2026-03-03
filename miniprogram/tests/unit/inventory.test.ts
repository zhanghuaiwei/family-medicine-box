import { mount } from '@vue/test-utils'
import Inventory from '../../src/pages/inventory.vue'

describe('inventory.vue', () => {
  test('renders inventory management title', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('h1').text()).toBe('库存管理')
  })

  test('renders inventory list section', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('.inventory-list').exists()).toBe(true)
  })

  test('renders low stock alerts section', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('.low-stock-alerts').exists()).toBe(true)
  })

  test('renders expiry alerts section', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('.expiry-alerts').exists()).toBe(true)
  })
})
