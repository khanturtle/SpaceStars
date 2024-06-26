import styles from './Background.module.css'

export default function BackGroundTextBox({ text }: { text: string }) {
  return <div className={styles['background-text']}>{text}</div>
}
