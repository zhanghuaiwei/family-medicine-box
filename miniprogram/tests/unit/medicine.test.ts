import { mount } from '@vue/test-utils'
import Medicine from '../../src/pages/medicine.vue'

describe('medicine.vue', () => {
  test('renders medicine management title', () => {
    const wrapper = mount(Medicine)
    expect(wrapper.find('h1').text()).toBe('药品管理')
  })

  test('renders medicine list section', () => {
    const wrapper = mount(Medicine)
    expect(wrapper.find('.medicine-list').exists()).toBe(true)
  })

  test('renders add medicine button', () => {
    const wrapper = mount(Medicine)
    expect(wrapper.find('.add-medicine-btn').exists()).toBe(true)
  })

  test('renders search bar', () => {
    const wrapper = mount(Medicine)
    expect(wrapper.find('.search-bar').exists()).toBe(true)
  })
})
