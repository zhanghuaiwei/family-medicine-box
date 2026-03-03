import { mount } from '@vue/test-utils'
import Inventory from '../../src/views/Inventory.vue'

describe('Inventory.vue', () => {
  test('renders inventory management title', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('h1').text()).toBe('库存管理')
  })

  test('renders inventory stats section', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('.inventory-stats').exists()).toBe(true)
  })

  test('renders inventory list section', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('.inventory-list').exists()).toBe(true)
  })

  test('renders low stock alerts section', () => {
    const wrapper = mount(Inventory)
    expect(wrapper.find('.low-stock-alerts').exists()).toBe(true)
  })
})
